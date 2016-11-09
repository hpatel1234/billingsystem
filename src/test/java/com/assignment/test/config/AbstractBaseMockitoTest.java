package com.assignment.test.config;
import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.reflect.Field;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractBaseMockitoTest {
	@BeforeClass
	public void setUp() {
		initMocks( this );
	}

	@BeforeMethod
    public void reset() throws IllegalAccessException {

        final Field[] fields = this.getClass().getDeclaredFields();
        for ( Field currentField : fields ) {
            if (currentField.isAnnotationPresent( Mock.class )) {
                currentField.setAccessible( true );
                final Object value = currentField.get( this );
                Mockito.reset( value );
            }
        }

    }
}
