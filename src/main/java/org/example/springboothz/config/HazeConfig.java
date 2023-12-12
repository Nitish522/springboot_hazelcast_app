package org.example.springboothz.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.KubernetesConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HazeConfig {

    @Value("${hazelcast.kubernetes.service-name}")
    private String serviceName;

    @Bean
    public HazelcastInstance hazelcastInstance(){
        Config config = new Config();
        config.setClusterName("my-app");
        config.setInstanceName("my-app");

        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);

        JoinConfig join = network.getJoin();

        join.getMulticastConfig().setEnabled(false);

        String env = System.getenv("environment");
        if("K8s".equals(env)){
            network.setPortAutoIncrement(false);
            KubernetesConfig kubernetesConfig = join.getKubernetesConfig();
            kubernetesConfig.setEnabled(true);
            kubernetesConfig.setProperty("service-name",serviceName)
                    .setProperty("namespace","default");
        }else {

            network.setPortAutoIncrement(true);
            join.getTcpIpConfig().setEnabled(true);
        }


        return Hazelcast.newHazelcastInstance(config);
    }
}
