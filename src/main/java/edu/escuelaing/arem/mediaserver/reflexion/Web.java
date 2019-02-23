package edu.escuelaing.arem.mediaserver.reflexion;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * @author Andres
 * Este es una anotacion creado para montar el framework
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Web {
	String value();

}
