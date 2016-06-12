package wildflyswarm.gzipfilter;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.config.undertow.FilterConfiguration;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.undertow.UndertowFraction;

public class App {

  private static final String GZIP_FILTER_KEY = "my-gzip";

  public static void main(String[] args) throws Exception {
    Container container = new Container();

    UndertowFraction undertowFraction = UndertowFraction.createDefaultFraction();

    undertowFraction
      .filterConfiguration(new FilterConfiguration().gzip(GZIP_FILTER_KEY)).subresources()
      .server("default-server").subresources()
      .host("default-host")
      .filterRef(GZIP_FILTER_KEY, f -> f.predicate("path-suffix['.css']"));

    container.fraction(undertowFraction);

    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addPackage(App.class.getPackage());
    deployment.staticContent();

    container.start().deploy(deployment);
  }

}
