package com.shipping.backend.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1","topics"})
@Configuration
public class Tut1Config {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tuto.topic");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut1Receiver receiver() {
            return new Tut1Receiver();
        }

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("lazy.#");
        }

    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

}

