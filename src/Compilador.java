import java.io.*;
import java.nio.file.Paths;

public class Compilador {

    private String    file;
    private String    cont;

    Compilador()
    {

        String os = System.getProperty("os.name").toLowerCase();



        if (os.contains("win"))
        {
            // Windows
            String currentPath = Paths.get("").toAbsolutePath().toString();
            this.file = currentPath + "\\resources\\init.py";

        }
        else
        {
            // Linux o macOS
            this.file = "resources/init.py";

        }

    }


    public void run(String _cont)
    {

        this.cont = _cont;

        try {

            File archivo = new File(this.file);


            FileWriter escritor = new FileWriter(archivo, false); // false para sobrescribir
            escritor.write(this.cont);
            escritor.close();

            System.out.println("Compilación completa");

        } catch (IOException e) {
            System.out.println("Error en Compilación");
            e.printStackTrace();
        }

        this.ejecutar();
    }


    private void ejecutar(){
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;

            String currentPath = Paths.get("").toAbsolutePath().toString();


            if (os.contains("win"))
            {
                // Windows
                String filePath = currentPath+"\\resources\\init.bat";
                processBuilder  = new ProcessBuilder("cmd.exe", "/c",filePath);

            }
            else
            {
                // Linux o macOS
                String filePath = "resources/init.sh";
                processBuilder = new ProcessBuilder("sh", filePath);

            }

            Process process = processBuilder.start();

            process.waitFor();

            System.out.println("Ejecucion exitosa");

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
