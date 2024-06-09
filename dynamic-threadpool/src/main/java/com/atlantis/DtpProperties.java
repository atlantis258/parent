package com.atlantis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "dtp")
public class DtpProperties {

    private List<DtpExecutorProperties> executor;

    public List<DtpExecutorProperties> getExecutor() {
        return executor;
    }

    public void setExecutor(List<DtpExecutorProperties> executor) {
        this.executor = executor;
    }

    public static class DtpExecutorProperties {

        private String poolName;
        private int corePoolSize = 10;
        private int maximumPoolSize = 100;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public String getPoolName() {
            return poolName;
        }

        public void setPoolName(String poolName) {
            this.poolName = poolName;
        }
    }
}
