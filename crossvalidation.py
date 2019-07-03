from sklearn import cross_validation
import numpy as np
from sklearn.model_selection import LeaveOneOut
from sklearn.metrics import accuracy_score

def k_fold(fts, lbls, model):
    kf = cross_validation.KFold(n=6705, n_folds=10)
    accuracy = []
    features = np.array(fts)
    labels = np.array(lbls)
    for train_index, test_index in kf:
        X_train, X_test = features[train_index], features[test_index]
        y_train, y_test = labels[train_index], labels[test_index]
        model.fit(X_train, y_train)
        prediction = model.predict(X_test)
        accuracy.append(accuracy_score(y_test, prediction))
    mean_acc = np.mean(accuracy);
    print("K-Fold mean accuracy:", str(mean_acc * 100), "%")


def leave_one_out(fts, lbls, model):
    loo = LeaveOneOut()
    features = np.array(fts)
    labels = np.array(lbls)
    loo.get_n_splits(features)
    accuracy = []
    for train_index, test_index in loo.split(features):
        X_train, X_test = features[train_index], features[test_index]
        y_train, y_test = labels[train_index], labels[test_index]
        model.fit(X_train, y_train)
        prediction = model.predict(X_test)
        accuracy.append(accuracy_score(y_test, prediction))
    mean_acc = np.mean(accuracy);
    print("Leave One Out mean accuracy:", str(mean_acc * 100), "%")
    

def holdout(features, labels, model):
    X_train, X_test, y_train, y_test = cross_validation.train_test_split(features, labels, test_size=3, random_state=7)
    model.fit(X_train, y_train)
    predictions = model.predict(X_test)
    print("Holdout mean accuracy:", str(accuracy_score(y_test, predictions) * 100), "%")