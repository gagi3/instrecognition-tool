package model;

import com.musicg.dsp.FastFourierTransform;
import com.musicg.wave.Wave;
import com.sun.media.sound.FFT;
import org.jtransforms.fft.DoubleFFT_1D;
import pl.edu.icm.jlargearrays.DoubleLargeArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class AudioFile {

    private String filename;
    private Instrument instrument;
    private Genre genre;
    private Drums drums;
    private double[] data;
    private double[] fft;

    public static void main(String[] args) throws IOException {
        AudioFile audioFile = new AudioFile("D:\\College\\Soft Computing\\Data\\IRMAS-TrainingData\\vio\\[vio][cla]2083__1.wav");
//        audioFile.filename = "D:\\College\\Soft Computing\\Data\\IRMAS-TrainingData\\flu\\155__[flu][nod][cou_fol]0416__3.wav";
//        audioFile.readClassifiers();
        System.out.println(audioFile.filename);
        System.out.println(audioFile.drums.toString());
        System.out.println(audioFile.instrument.toString());
        System.out.println(audioFile.genre.toString());
//        audioFile.transform();
//        System.out.println(Arrays.toString(audioFile.data));

//        System.out.println(audioFile.data[0]);
    }

    public AudioFile(String filename, Instrument instrument, Genre genre, Drums drums, double[] data) {
        this.filename = filename;
        this.instrument = instrument;
        this.genre = genre;
        this.drums = drums;
        this.data = data;
    }

    public AudioFile(String filename) throws IOException {
        this.filename = filename;
        readClassifiers();
        this.data = read();
        this.fft = transform();
//        inverse();
    }

    public AudioFile() {
    }

    public void readClassifiers() {
        readInstrument();
        readGenre();
        readDrums();
    }

    private void readInstrument() {
        if (this.filename.contains("\\[cel]") || this.filename.contains("__[cel]")) {
            this.instrument = Instrument.cel;
        } else if (this.filename.contains("\\[cla]") || this.filename.contains("__[cla]")) {
            this.instrument = Instrument.cla;
        } else if (this.filename.contains("\\[flu]") || this.filename.contains("__[flu]")) {
            this.instrument = Instrument.flu;
        } else if (this.filename.contains("\\[gac]") || this.filename.contains("__[gac]")) {
            this.instrument = Instrument.gac;
        } else if (this.filename.contains("\\[gel]") || this.filename.contains("__[gel]")) {
            this.instrument = Instrument.gel;
        } else if (this.filename.contains("\\[org]") || this.filename.contains("__[org]")) {
            this.instrument = Instrument.org;
        } else if (this.filename.contains("\\[pia]") || this.filename.contains("__[pia]")) {
            this.instrument = Instrument.pia;
        } else if (this.filename.contains("\\[sax]") || this.filename.contains("__[sax]")) {
            this.instrument = Instrument.sax;
        } else if (this.filename.contains("\\[tru]") || this.filename.contains("__[tru]")) {
            this.instrument = Instrument.tru;
        } else if (this.filename.contains("\\[vio]") || this.filename.contains("__[vio]")) {
            this.instrument = Instrument.vio;
        } else if (this.filename.contains("\\[voi]") || this.filename.contains("__[voi]")) {
            this.instrument = Instrument.voi;
        } else {
            this.instrument = null;
        }
    }

    private void readGenre() {
        if (this.filename.contains("][cla]")) {
            this.genre = Genre.cla;
        } else if (this.filename.contains("[cou_fol]")) {
            this.genre = Genre.cou_fol;
        } else if (this.filename.contains("[pop_roc]")) {
            this.genre = Genre.pop_roc;
        } else if (this.filename.contains("[jaz_blu]")) {
            this.genre = Genre.jaz_blu;
        } else if (this.filename.contains("[lat_sou]")) {
            this.genre = Genre.lat_sou;
        } else {
            this.genre = null;
        }
    }

    private void readDrums() {
        if (this.filename.contains("[dru]")) {
            this.drums = Drums.dru;
        } else if (this.filename.contains("[nod]")) {
            this.drums = Drums.nod;
        } else {
            this.drums = null;
        }
    }

    private double[] read() throws IOException {
        Wave wave = new Wave(this.filename);
        return wave.getNormalizedAmplitudes();
    }

    private double[] transform() throws IOException {
        DoubleLargeArray dba = new DoubleLargeArray(this.data);
        System.out.println(this.data.length);
        DoubleFFT_1D d = new DoubleFFT_1D(dba.length());
        DoubleLargeArray fft = new DoubleLargeArray(this.data.clone());
        double[] complex = new double[2 * this.data.length];
        System.arraycopy(this.data, 0, complex, 0, this.data.length);
        d.complexForward(complex);
        d.realForward(fft);
        this.fft = fft.getData();
        writeToFile(this.data, complex, this.fft);
        return this.fft;
    }

    private void inverse() {
        DoubleFFT_1D d = new DoubleFFT_1D(this.fft.length);
        d.realInverseFull(this.fft, false);
    }

    private void writeToFile(double[] dataBefore, double[] complexFFT, double[] realFFT) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("D:\\College\\Soft Computing\\txt.txt"));
        fileWriter.append("File name: ").append(this.filename).append("\n");
        if (this.instrument != null) fileWriter.append("Instrument: ").append(this.instrument.toString()).append("\n");
        if (this.genre != null) fileWriter.append("Genre: ").append(this.genre.toString()).append("\n");
        if (this.drums != null) fileWriter.append("Drums: ").append(this.drums.toString()).append("\n");
        fileWriter.append("Wave sample size: ").append(String.valueOf(this.data.length)).append("\n");
        fileWriter.append("Wave data: ").append(Arrays.toString(dataBefore)).append("\n");
        fileWriter.append("FFT complex sample size: ").append(String.valueOf(complexFFT.length)).append("\n");
        fileWriter.append("FFT complex data: ").append(Arrays.toString(complexFFT)).append("\n");
        fileWriter.append("FFT real sample size: ").append(String.valueOf(realFFT.length)).append("\n");
        fileWriter.append("FFT real data: ").append(Arrays.toString(realFFT)).append("\n");
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Drums getDrums() {
        return drums;
    }

    public void setDrums(Drums drums) {
        this.drums = drums;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public double[] getFft() {
        return fft;
    }

    public void setFft(double[] fft) {
        this.fft = fft;
    }
}
