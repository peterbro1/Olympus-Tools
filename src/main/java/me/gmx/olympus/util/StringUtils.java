  package me.gmx.olympus.util;
  
  import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;
import org.bukkit.ChatColor;
  
  
  
  
  
  
  
  
  
  
  
  public class StringUtils
  {
    public static String fixColors(String paramString) {
     if (paramString == null) {
       return "";
      }
     return ChatColor.translateAlternateColorCodes('&', paramString);
    }
  
  
  public static String colorize(String message)
{
    return message = ChatColor.translateAlternateColorCodes('&', message);
  }
  
  
  
  
  
    
   public static String stripColors(String paramString) { return ChatColor.stripColor(fixColors(paramString)); }
  
   private static final String LINEBREAK = "\n"; // or "\r\n";

   public static String wrap(String string, int lineLength) {
       StringBuilder b = new StringBuilder();
       for (String line : string.split(Pattern.quote(LINEBREAK))) {
           b.append(wrapLine(line, lineLength));
       }
       return b.toString();
   }

   private static String wrapLine(String line, int lineLength) {
       if (line.length() == 0) return LINEBREAK;
       if (line.length() <= lineLength) return line + LINEBREAK;
       String[] words = line.split(" ");
       StringBuilder allLines = new StringBuilder();
       StringBuilder trimmedLine = new StringBuilder();
       for (String word : words) {
           if (trimmedLine.length() + 1 + word.length() <= lineLength) {
               trimmedLine.append(word).append(" ");
           } else {
               allLines.append(trimmedLine).append(LINEBREAK);
               trimmedLine = new StringBuilder();
               trimmedLine.append(word).append(" ");
           }
       }
       if (trimmedLine.length() > 0) {
           allLines.append(trimmedLine);
       }
       allLines.append(LINEBREAK);
       return allLines.toString();
   }
   //-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
  
   public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
	               if (str == null) {
	                   return null;
	               }
	               if (newLineStr == null) {
	                   newLineStr = SystemUtils.LINE_SEPARATOR;
	               }
	               if (wrapLength < 1) {
	                   wrapLength = 1;
	               }
	               int inputLineLength = str.length();
	               int offset = 0;
	               StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
	               
	               while (inputLineLength - offset > wrapLength) {
	                   if (str.charAt(offset) == ' ') {
	                       offset++;
	                       continue;
	                   }
	                   int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);
	       
	                   if (spaceToWrapAt >= offset) {
	                       // normal case
	                       wrappedLine.append(str.substring(offset, spaceToWrapAt));
	                       wrappedLine.append(newLineStr);
	                       offset = spaceToWrapAt + 1;
	                       
	                   } else {
	                       // really long word or URL
	                       if (wrapLongWords) {
	                           // wrap really long word one line at a time
	                           wrappedLine.append(str.substring(offset, wrapLength + offset));
	                           wrappedLine.append(newLineStr);
	                           offset += wrapLength;
	                       } else {
	                           // do not wrap really long word, just extend beyond limit
	                           spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
	                           if (spaceToWrapAt >= 0) {
	                               wrappedLine.append(str.substring(offset, spaceToWrapAt));
	                               wrappedLine.append(newLineStr);
	                               offset = spaceToWrapAt + 1;
	                           } else {
	                               wrappedLine.append(str.substring(offset));
	                               offset = inputLineLength;
	                           }
	                       }
	                   }
	               }
	       
	               // Whatever is left in line is short enough to just pass through
	               wrappedLine.append(str.substring(offset));
	       
	               return wrappedLine.toString();
	           }
  
  
  
    
    public static String formatDoubleString(double paramDouble) {
     DecimalFormat decimalFormat = new DecimalFormat("#.##");
     decimalFormat.setMaximumIntegerDigits(32);
     if (paramDouble % (int)paramDouble == 0.0D) {
       return decimalFormat.format(paramDouble).split("\\.")[0];
      }
     return decimalFormat.format(paramDouble).replace(",", ".");
    }
  }
