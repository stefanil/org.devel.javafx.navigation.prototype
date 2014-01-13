/**
 * 
 */
package org.devel.javafx.navigation.prototype.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogOutputStream extends ByteArrayOutputStream {//java.io.OutputStream {

    private String  lineSeparator;
    private Logger  logger;
    private Level   level;

    public LogOutputStream(Logger logger, Level level) {
        super();
        this.logger = logger;
        this.level = level;
        this.lineSeparator = System.getProperty("line.separator");
    }

    @Override
    public void flush() throws IOException {

        String record;
        synchronized (this) {
            super.flush();
            record = this.toString();
            super.reset();

            if ((record.length() == 0) || record.equals(this.lineSeparator)) {
                // avoid empty records 
                return;
            }

            this.logger.logp(this.level, "", "", record);
        }
    }
}
