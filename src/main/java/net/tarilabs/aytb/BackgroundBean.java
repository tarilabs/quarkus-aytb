package net.tarilabs.aytb;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;
import net.tarilabs.aytb.hue.GroupsAction;

@Singleton
public class BackgroundBean {

    private static final Logger LOG = LoggerFactory.getLogger(BackgroundBean.class);

    @ConfigProperty(name = "hue.username")
    String username;
    @ConfigProperty(name = "hue.group")
    String group;

    @Inject
    CaptureActionBean capture;

    @Inject
    @RestClient
    HueClient hueClient;

    // brutal
    private Map<Color, Color> buffer = Collections.synchronizedMap(new LinkedHashMap<>());

    void onStart(@Observes StartupEvent event) {
        buffer.put(Color.decode("#FF0000"), Color.decode("#FF0000"));
        buffer.put(Color.decode("#00FF00"), Color.decode("#00FF00"));
        buffer.put(Color.decode("#0000FF"), Color.decode("#0000FF"));

        Runnable task = () -> {
            while(true) {
                String ocr = capture.capture();
                List<Color> colors = BotParserUtils.parse(ocr);
                synchronized(buffer) {
                    for (Color c : colors) {
                        buffer.putIfAbsent(c, c);
                    }
                }
                LOG.info("OCR: {}", colors);
                if (colors.isEmpty()) {
                    LOG.debug("OCR text: {}", ocr);
                }
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(task).start();
    }

    @Scheduled(every = "10s")
    void increment() {
        Optional<Color> color = null;
        synchronized (buffer) {
            color = buffer.entrySet().stream().map(Entry::getKey).findFirst();
            color.ifPresent(buffer::remove);
        }
        if (color.isPresent()) {
            GroupsAction action = GroupsAction.fromColor(color.get());
            String result = hueClient.groupsAction(username, group, action);
            LOG.info(result);
        } else {
            GroupsAction action = GroupsAction.fromColor(Color.decode("#FFFFFF"));
            String result = hueClient.groupsAction(username, group, action);
            LOG.info("empty buffer, {}", result);
        }
    }
}
