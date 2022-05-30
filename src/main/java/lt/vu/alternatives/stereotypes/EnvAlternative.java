package lt.vu.alternatives.stereotypes;

import lt.vu.enums.EnvType;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

@Stereotype
@Alternative
@Target(TYPE)
@Retention(RUNTIME)
public @interface EnvAlternative {
    EnvType[] value();
}
