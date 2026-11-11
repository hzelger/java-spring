package swt5.spring.basics.ioc.util;

import swt5.spring.basics.ioc.logic.Log;

@Log(Log.Type.STANDARD)
public class ConsoleLogger implements Logger {

  private String prefix = "Log";

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void log(String msg) {
    System.out.format("%s: %s%n", prefix, msg);
  }
}
