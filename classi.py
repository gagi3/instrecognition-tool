import numpy as np
from sklearn import neighbors

import read

def unbox(data):
    filename = np.array(list(map(lambda n: n[0], data)))
    features = np.array(list(map(lambda n: n[1], data))).astype(np.float32)
    labels = np.array(list(map(lambda n: n[2], data)))
    return filename, features, labels

def unboxtrained(data):
    filename = np.array(list(map(lambda n: n[0], data)))
    features = np.array(list(map(lambda n: n[1], data))).astype(np.float32)
    predicted_labels = np.array(list(map(lambda n: n[2], data)))
    actual_labels = np.array(list(map(lambda n: n[3], data)))
    return filename, features, predicted_labels, actual_labels

def unboxsingle(data):
    filename = data[0]
    feature = data[1]
    predicted_label = data[2]
    actual_label = data[3]

    return filename, feature, predicted_label, actual_label

def train(data):
    filename, features, labels = unbox(data)
    knn = neighbors.KNeighborsClassifier(n_neighbors = 97, metric = 'minkowski')
    knn.fit(features, labels)
    return knn

def predict(features, knn):
    predicted = knn.predict(features)
    return predicted

def testone(data, knn):
    filename, features, predicted_labels, actual_labels = unboxsingle(data)
    features = features.reshape(1, -1)
    predicted = predictone(features, knn)
    return predicted

def predictone(features, knn):
    predicted = knn.predict(features)
    return predicted


def test(data, knn):
    filename, features, predicted_labels, actual_labels = unboxtrained(data)
    predicted = predict(features, knn)
    return predicted

def testandverify(data, knn):
    acc = 0
    for d in data:
        filename, feature, predicted_label, actual_label = unboxsingle(d)
        feature = feature.reshape(1, -1)
        predicted = predict(feature, knn)
        if (predicted == actual_label):
            acc += 1
        elif (predicted == predicted_label):
            acc += 1
        
        #print(filename, predicted_label, actual_label)
    return acc/len(data)*100
