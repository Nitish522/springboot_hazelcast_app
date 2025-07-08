package org.example.springboothz.viewResolver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ViewHolder {

    private String view = "";

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public enum Views{

        INTERNAL("X-view","internal");

        public String header;
        public String val;

        Views(String header,String val){
            this.header=header;
            this.val=val;
        }
    }
}
