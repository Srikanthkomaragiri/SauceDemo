package Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;

public class LogManagerUtil {

    private static final Map<String, Logger> loggerMap = new HashMap<>();
    private static final String LOG_DIR = "target/Logs/";

    public static Logger getLogger(String featureName) {
        if (loggerMap.containsKey(featureName)) {
            return loggerMap.get(featureName);
        }

        try {
            String logFilePath = LOG_DIR + featureName + ".log";
            new File(LOG_DIR).mkdirs();

            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Configuration config = ctx.getConfiguration();

            PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{HH:mm:ss} %-5level %msg%n")
                .build();

            RollingFileAppender appender = RollingFileAppender.newBuilder()
                .setName(featureName + "Appender")
                .withFileName(logFilePath)
                .withFilePattern(LOG_DIR + featureName + "-%d{yyyy-MM-dd-HH-mm}.log")
                .setLayout(layout)
                .withPolicy(SizeBasedTriggeringPolicy.createPolicy("1MB"))
                .setConfiguration(config)
                .build();

            appender.start();
            config.addAppender(appender);

            AppenderRef ref = AppenderRef.createAppenderRef(featureName + "Appender", null, null);
            LoggerConfig loggerConfig = LoggerConfig.createLogger(false, org.apache.logging.log4j.Level.INFO,
                    featureName, "true", new AppenderRef[]{ref}, null, config, null);

            loggerConfig.addAppender(appender, null, null);
            config.addLogger(featureName, loggerConfig);
            ctx.updateLoggers();

            Logger logger = LogManager.getLogger(featureName);
            loggerMap.put(featureName, logger);
            return logger;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create feature-wise logger", e);
        }
    }
}
