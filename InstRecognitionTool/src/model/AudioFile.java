package model;

import com.musicg.dsp.FastFourierTransform;
import com.musicg.wave.Wave;
import org.jtransforms.fft.DoubleFFT_1D;

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

    public static void main(String[] args) throws IOException {
        AudioFile audioFile = new AudioFile("D:\\College\\Soft Computing\\Data\\IRMAS-TrainingData\\flu\\155__[flu][nod][cou_fol]0416__3.wav");
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
        this.data = transform();
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

    private double[] transform() throws IOException {
        FastFourierTransform fft = new FastFourierTransform();
        Wave wave = new Wave(this.filename);
        DoubleFFT_1D d = new DoubleFFT_1D(1);
        this.data = wave.getNormalizedAmplitudes();
        double[] complex = this.data;
        double[] real = this.data;
        d.complexForward(complex);
        d.realForwardFull(real);
        writeToFile(this.data, complex, real);
        return this.data;
    }

    private void writeToFile(double[] dataBefore, double[] complexFFT, double[] realFFT) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("D:\\College\\Soft Computing\\txt.txt"));
        fileWriter.append("Instrument: ").append(this.instrument.toString()).append("\n");
        fileWriter.append("Genre: ").append(this.genre.toString()).append("\n");
        fileWriter.append("Drums: ").append(this.drums.toString()).append("\n");
        fileWriter.append("Size: ").append(String.valueOf(this.data.length)).append("\n");
        fileWriter.append("Wave data: ").append(Arrays.toString(dataBefore)).append("\n");
        fileWriter.append("FFT complex data: ").append(Arrays.toString(complexFFT)).append("\n");
        fileWriter.append("FFT real data: ").append(Arrays.toString(realFFT)).append("\n");
        assert dataBefore == complexFFT;

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
}
