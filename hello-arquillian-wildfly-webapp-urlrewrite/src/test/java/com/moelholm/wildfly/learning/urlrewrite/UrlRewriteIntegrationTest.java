package com.moelholm.wildfly.learning.urlrewrite;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.moelholm.arquillian.support.WildFlyEmbeddedArquillianRunner;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class UrlRewriteIntegrationTest {

    @ArquillianResource
    private URL url;

    @Deployment
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap.create(WebArchive.class, "myweb.war") //
                .addClass(WildFlyEmbeddedArquillianRunner.class) //
                .addClass(RewriteServletFilter.class) //
                .addAsWebResource(new StringAsset("from hello world"), "from/index.txt") //
                .addAsWebResource(new StringAsset("TO (!!) hello world"), "to/index.txt") //
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(war.toString(true));

        return war;
    }

    @Test
    @RunAsClient
    public void clientsideTest() throws Exception {

        // Given
        URL urlToIndexHtml = new URL(url, "from/index.txt");

        // When
        String htmlContents = get(urlToIndexHtml);

        // Then
        assertEquals("TO (!!) hello world", htmlContents);
    }

    private static String get(URL urlToGet) throws IOException {
        StringBuilder htmlContents = new StringBuilder();

        try (InputStream is = urlToGet.openStream(); Scanner scanner = new Scanner(is)) {

            scanner.useDelimiter("\\Z");

            while (scanner.hasNext()) {
                htmlContents.append(scanner.next());
            }
        }

        return htmlContents.toString();
    }

}
