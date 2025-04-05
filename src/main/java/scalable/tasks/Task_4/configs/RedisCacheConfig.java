package scalable.tasks.Task_4.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import scalable.tasks.Task_4.models.Company;
import scalable.tasks.Task_4.models.Country;
import scalable.tasks.Task_4.models.Employee;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // Default cache configuration
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Default TTL for all caches
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new Jackson2JsonRedisSerializer<>(Object.class)));

        // Custom configurations for different entities
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // Cache configuration for Students
        cacheConfigurations.put("company_mohamed_438078",
                defaultConfig.serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new Jackson2JsonRedisSerializer<>(Company.class))));


        // Cache configuration for Courses
        cacheConfigurations.put("country_mohamed_438078", defaultConfig.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer<>(Country.class))));

        // Cache configuration for Instructors
        cacheConfigurations.put("employee_mohamed_438078", defaultConfig.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer<>(Employee.class))));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig) // Default settings
                .withInitialCacheConfigurations(cacheConfigurations) // Custom per-cache configurations
                .build();
    }
}
