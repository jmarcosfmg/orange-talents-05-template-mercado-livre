package com.orangetalents.treinomercadolivre.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = { CampoVerificadoValidator.class })
public @interface CampoVerificado {

	String message() default "Valor informado não existe";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> classe();

	String atributo();

}
