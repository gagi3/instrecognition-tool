import numpy as np
import sklearn
import librosa
import glob
import classi

def fext():
    sample_rate = 44100
    data = []

    files = glob.glob('D:\College\Soft Computing\Data\IRMAS-TrainingData\*\*.wav')
    np.random.shuffle(files)
    for filename in files:
        music, sample_rate = librosa.load(filename, sr = sample_rate)

        mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
        average = np.mean(mfccs, axis = 1)
        feature = average.reshape(20)

        if filename[50:53] == 'cel':
            label = 1
        elif filename[50:53] == 'cla':
            label = 2   
        elif filename[50:53] == 'flu':
            label = 3
        elif filename[50:53] == 'gac':
            label = 4
        elif filename[50:53] == 'gel':
            label = 5
        elif filename[50:53] == 'org':
            label = 6
        elif filename[50:53] == 'pia':
            label = 7
        elif filename[50:53] == 'sax':
            label = 8
        elif filename[50:53] == 'tru':
            label = 9
        elif filename[50:53] == 'vio':
            label = 10
        elif filename[50:53] == 'voi':
            label = 11

        data2 = [filename, feature, label]
        #print(data2)
        data.append(data2)
    
    return data

def fextonetest():
    sample_rate = 44100
    
    filename = 'D:\College\Soft Computing\Data\IRMAS-TestingData-Part1\Part1\(02) dont kill the whale-15.wav'

    music, sample_rate = librosa.load(filename, sr = sample_rate)
    mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
    average = np.mean(mfccs, axis = 1)
    feature = average.reshape(20)
    predicted_label = 0

    txtf = filename[:-3] + 'txt'
    file = open(txtf, "r")
    inst = file.readlines()
    # print(inst)
    # inst = inst.split('\t')
    # print(inst)
    if len(inst) == 2:
        predicted_label = label(inst[0])
        actual_label = label(inst[1])
    else:
        predicted_label = label(inst[0])
        actual_label = 0

    file.close()
    data2 = [filename, feature, predicted_label, actual_label]
    return data2

def label(labelstr):
    if labelstr[:-2] == 'cel':
        return 1
    elif labelstr[:-2] == 'cla':
        return 2   
    elif labelstr[:-2] == 'flu':
        return 3
    elif labelstr[:-2] == 'gac':
        return 4
    elif labelstr[:-2] == 'gel':
        return 5
    elif labelstr[:-2] == 'org':
        return 6
    elif labelstr[:-2] == 'pia':
        return 7
    elif labelstr[:-2] == 'sax':
        return 8
    elif labelstr[:-2] == 'tru':
        return 9
    elif labelstr[:-2] == 'vio':
        return 10
    elif labelstr[:-2] == 'voi':
        return 11
    else:
        return 0
    return 0
            
def fexttest1():
    sample_rate = 44100
    data = []
    
    files = glob.glob('D:\College\Soft Computing\Data\IRMAS-TestingData-Part1\Part1\*.wav')
    np.random.shuffle(files)

    for filename in files:
        music, sample_rate = librosa.load(filename, sr = sample_rate)
        mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
        average = np.mean(mfccs, axis = 1)
        feature = average.reshape(20)

        predicted_label = 0

        txtf = filename[:-3] + 'txt'
        file = open(txtf, "r")
        inst = file.readlines()
        # print(inst)
        # inst = inst.split('\t')
        # print(inst)
        if len(inst) == 2:
            predicted_label = label(inst[0])
            actual_label = label(inst[1])
        else:
            predicted_label = label(inst[0])
            actual_label = 0
        
        data2 = [filename, feature, predicted_label, actual_label]
        data.append(data2)
        file.close()
    return data


def fexttest2():
    sample_rate = 44100
    data = []
    
    files = glob.glob('D:\College\Soft Computing\Data\IRMAS-TestingData-Part2\IRTestingData-Part2\*.wav')
    np.random.shuffle(files)

    for filename in files:
        music, sample_rate = librosa.load(filename, sr = sample_rate)
        mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
        average = np.mean(mfccs, axis = 1)
        feature = average.reshape(20)
        predicted_label = 0

        txtf = filename[:-3] + 'txt'
        file = open(txtf, "r")
        inst = file.readlines()
        # print(inst)
        # inst = inst.split('\t')
        # print(inst)
        if len(inst) == 2:
            predicted_label = label(inst[0])
            actual_label = label(inst[1])
        else:
            predicted_label = label(inst[0])
            actual_label = 0

        data2 = [filename, feature, predicted_label, actual_label]
        data.append(data2)
        file.close()

    return data

def fexttest3():
    sample_rate = 44100
    data = []
    
    files = glob.glob('D:\College\Soft Computing\Data\IRMAS-TestingData-Part3\Part3\*.wav')
    np.random.shuffle(files)

    for filename in files:
        music, sample_rate = librosa.load(filename, sr = sample_rate)
        mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
        average = np.mean(mfccs, axis = 1)
        feature = average.reshape(20)
        predicted_label = 0

        txtf = filename[:-3] + 'txt'
        file = open(txtf, "r")
        inst = file.readlines()
        # print(inst)
        # inst = inst.split('\t')
        # print(inst)
        if len(inst) == 2:
            predicted_label = label(inst[0])
            actual_label = label(inst[1])
        else:
            predicted_label = label(inst[0])
            actual_label = 0
        
        data2 = [filename, feature, predicted_label, actual_label]
        data.append(data2)
        file.close()
    return data

def fextcustom():
    sample_rate = 44100
    data = []
    
    files = glob.glob('D:\College\Soft Computing\Data\DEFENSE\*.wav')
    np.random.shuffle(files)

    for filename in files:
        music, sample_rate = librosa.load(filename, sr = sample_rate)
        mfccs = librosa.feature.mfcc(y = music, sr = sample_rate)
        average = np.mean(mfccs, axis = 1)
        feature = average.reshape(20)

        predicted_label = 0

        txtf = filename[:-3] + 'txt'
        file = open(txtf, "r")
        inst = file.readlines()
        # print(inst)
        # inst = inst.split('\t')
        # print(inst)
        if len(inst) == 2:
            predicted_label = label(inst[0])
            actual_label = label(inst[1])
        else:
            predicted_label = label(inst[0])
            actual_label = 0
        
        data2 = [filename, feature, predicted_label, actual_label]
        data.append(data2)
        file.close()
    return data