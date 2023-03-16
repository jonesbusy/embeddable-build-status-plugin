package org.jenkinsci.plugins.badge;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmbeddableBadgeConfigTest {
    @Test
    public void testConstructor() {
        String id = "testId";
        EmbeddableBadgeConfig embeddableBadgeConfig = new EmbeddableBadgeConfig(id);
        assertThat(embeddableBadgeConfig.getID(), is(id));
        assertThat(embeddableBadgeConfig.getSubject(), IsNull.nullValue());
        assertThat(embeddableBadgeConfig.getStatus(), IsNull.nullValue());
        assertThat(embeddableBadgeConfig.getAnimatedOverlayColor(), IsNull.nullValue());
        assertThat(embeddableBadgeConfig.getLink(), IsNull.nullValue());
        assertThat(embeddableBadgeConfig.getColor(), IsNull.nullValue());
    }

    @Test
    public void testGetAnimatedOverlayColorBlueForRunning() {
        EmbeddableBadgeConfig embeddableBadgeConfig = new EmbeddableBadgeConfig("testId");
        embeddableBadgeConfig.setStatus("running");
        assertThat(embeddableBadgeConfig.getAnimatedOverlayColor(), is("blue"));
    }

    @ParameterizedTest
    @CsvSource({
        "failing,red",
        "passing,brightgreen",
        "unstable,yellow",
        "aborted,aborted",
        "running,blue"
    })
    public void testGetColor(String status, String expected) {
        EmbeddableBadgeConfig embeddableBadgeConfig = new EmbeddableBadgeConfig("testId");
        embeddableBadgeConfig.setStatus(status);
        assertThat(embeddableBadgeConfig.getColor(), is(expected));
    }
}