package org.example.springboothz.viewResolver;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.example.springboothz.viewResolver.InternalField.DEFAULT_FILTER;

@RestControllerAdvice
public class JsonViewAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Autowired
    private ViewHolder viewHolder;

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        Set<Class<? extends Annotation>> toExclude = new HashSet<>();
        if (!ViewHolder.Views.INTERNAL.val.equals(viewHolder.getView())) {
            toExclude.add(InternalField.class);
        }
        AnnotationBasedFilter filter = new AnnotationBasedFilter(toExclude);
        FilterProvider filters = new SimpleFilterProvider().addFilter(DEFAULT_FILTER, filter);
        bodyContainer.setFilters(filters);
    }

    static class AnnotationBasedFilter extends SimpleBeanPropertyFilter {

        private final Set<Class<? extends Annotation>> markersToExclude;

        public AnnotationBasedFilter(Set<Class<? extends Annotation>> markersToExclude) {
            this.markersToExclude = markersToExclude;
        }

        @Override
        protected boolean include(PropertyWriter writer) {
            for (Class<? extends Annotation> anno : markersToExclude) {
                if (writer.getAnnotation(anno) != null || writer.getMember()
                        .hasAnnotation(anno)) {
                    return false;
                }
            }
            return true;
        }
    }
}
