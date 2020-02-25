package tamagotchi.data.saving;

import java.io.*;

public class SavingLoadingService {
    public static void saveToFle(File file, AppStateDto appStateDto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(appStateDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppStateDto loadFromFile(File file) {
        AppStateDto appStateDto = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            appStateDto = (AppStateDto) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return appStateDto;
    }
}
