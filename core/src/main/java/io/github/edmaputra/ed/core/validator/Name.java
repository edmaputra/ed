package io.github.edmaputra.ed.core.validator;

import io.github.edmaputra.ed.core.constant.DbColumn;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@NotBlank(message = "Should not Blank")
@Size(max = DbColumn.NAME_LENGTH, min = 2, message = "Length should be 2 - " + DbColumn.NAME_LENGTH)
public @interface Name {

  String message() default "";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

}
