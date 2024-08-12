package com.librarymanager.BookService.configs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.thoughtworks.xstream.XStream;

public class AxonConfigTest {
    @Test
    public void testXStreamBeanConfiguation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AxonConfig.class);
        XStream xStream = context.getBean(XStream.class);

        assertNotNull(xStream);
        context.close();
    }
}
