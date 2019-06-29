from sklearn import model_selection
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.ensemble import RandomForestClassifier

import classi as cp


def print_confusion_matrix(labels, prediction):
    print(confusion_matrix(labels, prediction))
    return


def print_classification_report(labels, prediction):
    print(classification_report(labels, prediction))
    return


def print_cross_validation_score(cv_score):
    print("Cross-validation score:", cv_score)
    print("Mean cross-validation score:", cv_score.mean())
    return


def accuracy(features, labels, rfc):
    cv_score = model_selection.cross_val_score(rfc, features, labels, cv=10)
    return cv_score


def train(data):
    filename, features, labels = cp.unbox(data)
    rfc = RandomForestClassifier()
    rfc.fit(features, labels)
    return rfc


def test(data, rfc):
    filename, features, labels = cp.unbox(data)
    prediction = rfc.predict(features)
    return prediction
