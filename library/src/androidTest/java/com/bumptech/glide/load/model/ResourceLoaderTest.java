package com.bumptech.glide.load.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import android.net.Uri;

import com.bumptech.glide.load.data.DataFetcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Tests for the {@link com.bumptech.glide.load.model.ResourceLoader} class.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class ResourceLoaderTest {

    @Mock ModelLoader<Uri, Object> uriLoader;
    @Mock DataFetcher<Object> fetcher;

    private ResourceLoader<Object> loader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loader = new ResourceLoader<Object>(Robolectric.application, uriLoader);
    }

    @Test
    public void testCanHandleId() {
        int id = android.R.drawable.star_off;
        Uri contentUri = Uri.parse("android.resource://android/drawable/star_off");
        when(uriLoader.getDataFetcher(eq(contentUri), anyInt(), anyInt())).thenReturn(fetcher);

        assertTrue(loader.handles(id));
        assertEquals(fetcher, loader.getDataFetcher(id, 100, 100));
    }
}