package fourier;

import model.AudioFile;
import model.Instrument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Fourier {

    private HashMap<Instrument, ArrayList<AudioFile>> transforms;
    private final String directory = "D:\\College\\Soft Computing\\Data";
    private String dataPart;
    private String subfolder;
    private String filename;

    public static void main(String[] args) throws IOException {
        Fourier fourier = new Fourier("IRMAS-TrainingData");
        System.out.println(fourier.getTransforms().keySet());
        for (Instrument i : fourier.getTransforms().keySet()) {
            System.out.println("Instrument: " + i.toString());
            System.out.println("Sample size: " + fourier.getTransforms().get(i).size());
        }
    }

    public Fourier(HashMap<Instrument, ArrayList<AudioFile>> transforms, String dataPart, String subfolder, String filename) {
        this.transforms = transforms;
        this.dataPart = dataPart;
        this.subfolder = subfolder;
        this.filename = filename;
    }

    public Fourier(String dataPart, String subfolder, String filename) throws IOException {
        this.dataPart = dataPart;
        this.subfolder = subfolder;
        this.filename = filename;
        AudioFile audioFile = new AudioFile(this.directory + "\\" + dataPart + "\\" + subfolder + "\\" + filename);
        if (this.transforms != null || !this.transforms.get(audioFile.getInstrument()).isEmpty() || this.transforms.get(audioFile.getInstrument()) != null) {
            ArrayList<AudioFile> af = transforms.get(audioFile.getInstrument());
            af.add(audioFile);
        }
    }

    public Fourier(String dataPart) throws IOException {
        this.dataPart = dataPart;
        listen();
    }

    public Fourier() {
        transforms = new HashMap<>();
    }

    private void listen() throws IOException {
        if (this.dataPart.contains("Training")) {
            training();
        } else if (this.dataPart.contains("Testing")) {
            testing();
        } else {
            throw new IOException("Wrong folder!");
        }
    }

    private void training() throws IOException {
        String fullDir = this.directory + "\\" + this.dataPart;
        File[] files = new File(fullDir).listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                File[] samples = new File(f.getAbsolutePath()).listFiles();
                for (File sample : samples) {
                    if (sample.getAbsolutePath().contains(".wav") && sample.isFile() && !sample.getAbsolutePath().contains("__[")) {
                        AudioFile audioFile = new AudioFile(sample.getAbsolutePath());
                        audioFile.setData(new double[]{0.0});
                        if (this.transforms == null) {
                            this.transforms = new HashMap<>();
                        } else if (this.transforms.get(audioFile.getInstrument()) == null) {
                            ArrayList<AudioFile> af = new ArrayList<>();
                            af.add(audioFile);
                            this.transforms.put(audioFile.getInstrument(), af);
                        } else {
                            ArrayList<AudioFile> af = this.transforms.get(audioFile.getInstrument());
                            af.add(audioFile);
                        }
                        System.out.println(audioFile.getFilename());
                    }
                }
            }
        }
    }

    private void testing() {

    }

    public HashMap<Instrument, ArrayList<AudioFile>> getTransforms() {
        return transforms;
    }

    public void setTransforms(HashMap<Instrument, ArrayList<AudioFile>> transforms) {
        this.transforms = transforms;
    }

    public String getDirectory() {
        return directory;
    }

    public String getDataPart() {
        return dataPart;
    }

    public void setDataPart(String dataPart) {
        this.dataPart = dataPart;
    }

    public String getSubfolder() {
        return subfolder;
    }

    public void setSubfolder(String subfolder) {
        this.subfolder = subfolder;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
