package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private  static Properties properties;
    //static initializer runs the block only once for the whole project
    //instance initializer runs the block for every object creation from class
    static {

       /**  public static void main(String[] args) throws IOException {*/

        //filePath -> the directory of your properties file

        String filePath = "src/test/resources/properties/digitalbank.properties";//Provide the path to the file


        //this a class that enable you to read files
        //it throws a checked exception

        //FileInputStream class is used for reading binary data from a file.

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(inputStream);

        } catch (IOException e) {
            System.out.println("File not found");

        }
        //Always close the `FileInputStream` once you're done with it to release system resources.
        // This is typically done in a `finally` block to ensure proper cleanup.
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**System.out.println(properties.get("my_name"));
        //System.out.println(properties.get("browser"));
        //System.out.println(properties.get("environment"));*/
    }
    public static String getPropertiesValue(String key){
        return properties.getProperty(key);
    }
}
