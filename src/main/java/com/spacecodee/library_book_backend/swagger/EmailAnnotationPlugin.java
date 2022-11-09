package com.spacecodee.library_book_backend.swagger;

import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.builders.StringElementFacetBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;

@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class EmailAnnotationPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(@NonNull DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<Email> email = annotationFromBean(context, Email.class);
        Optional<NotBlank> notBlank = annotationFromBean(context, NotBlank.class);

        if (email.isPresent()) {
            context.getSpecificationBuilder()
                   .facetBuilder(StringElementFacetBuilder.class)
                   .pattern(email.get().regexp());
            context.getSpecificationBuilder().example("email@email.com");
        }

        if (notBlank.isPresent()) {
            context.getSpecificationBuilder()
                   .facetBuilder(StringElementFacetBuilder.class)
                   .minLength(1);
        }
    }
}