from sklearn.datasets import *

import matplotlib.pyplot as plt
get_ipython().magic('matplotlib inline')
import random
import numpy as np
import pandas as pd
from sklearn import datasets, svm, cross_validation, tree, preprocessing, metrics
import sklearn.ensemble as ske

data = pd.read_excel("titanic.xls")
data.head()
from sklearn.tree import DecisionTreeClassifier
model = DecisionTreeClassifier()
data['survived'].mean()
y= data['survived'].values
data['age'].unique()
data = data.dropna()
new = data.copy()
new['age'].unique()
new['age'] = new['age'].apply(lambda x: mean_age if x=='nan' else x)
new['age'].unique()
mean_age = data['age'].mean()
data['age'].unique()==mean_age
data['pclass']
data['embarked'].unique()
data['embarked'] = data['embarked'].apply(lambda x: ord(x) )
data['embarked'].unique()
data['sex'] = data['sex'].apply(lambda x: 1 if x== "male" else 0)
data['sex'].unique()
X = data.drop(['survived', 'name'], axis = 1).values
y = data['survived'].values
X_train, X_test, y_train, y_test = cross_validation.train_test_split(X,y,test_size=0.2)

clf_dt = tree.DecisionTreeClassifier(max_depth=10)
clf_dt.fit (X_train, y_train)

shuffle_validator = cross_validation.ShuffleSplit(len(X), n_iter=20, test_size=0.2, random_state=0)
def test_classifier(clf):
    scores = cross_validation.cross_val_score(clf, X, y, cv=shuffle_validator)
    print("Accuracy: %0.4f (+/- %0.2f)" % (scores.mean(), scores.std()))
test_classifier(clf_dt)

data.head()
from sklearn.tree import export_graphviz
tree.export_graphviz(clf_dt,out_file='tree_case1.dot')  

data.head()

##################### Case 2 ######################

clf_dt.feature_importances_

# the classes with least importance are embarked, parch, sibsp

X = data.drop(['survived', 'name', 'parch', 'sibsp', 'embarked'], axis = 1).values
X_train, X_test, y_train, y_test = cross_validation.train_test_split(X,y,test_size=0.2)
clf_dt = tree.DecisionTreeClassifier(max_depth=10)
clf_dt.fit (X_train, y_train)
test_classifier(clf_dt)
tree.export_graphviz(clf_dt,out_file='tree_case2.dot') 

##################### Case 3 ######################

data['new'] = data['pclass'].apply(lambda x: x*2 if x==1 else x)
data['new'] = data['pclass'].apply(lambda x: x*3 if x==2 else x)
data['new'] = data['pclass'].apply(lambda x: x*4 if x==3 else x)
data['new']
X = data.drop(['survived', 'name'], axis = 1).values
X_train, X_test, y_train, y_test = cross_validation.train_test_split(X,y,test_size=0.2)
clf_dt = tree.DecisionTreeClassifier(max_depth=10)
clf_dt.fit (X_train, y_train)
test_classifier(clf_dt)
tree.export_graphviz(clf_dt,out_file='tree_case3.dot')
data.head()