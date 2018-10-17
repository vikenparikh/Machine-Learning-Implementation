# -*- coding: utf-8 -*-
"""
Created on Wed Feb  16 22:07:13 2018

@author: Viken
"""

import numpy as np
from sklearn.svm import SVR
from sklearn import datasets		
from sklearn import svm    			
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt 
data1 = datasets.load_iris()
x = data1.data[:, :2] 
y = data1.target
plt.scatter(x[:, 0], x[:, 1], c=y, cmap=plt.cm.coolwarm)
plt.xlabel('Length of Sepal')
plt.ylabel('Width of sepal')
plt.title('Sepal length v/s Sepal width')
plt.show()

X = data1.data[:, :2]  
y = data1.target
C = 1.0  
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.4)

svc = svm.SVC(kernel='linear', C=C).fit(X_train, y_train)
rbf_svc = svm.SVC(kernel='rbf', gamma=0.5, C=C).fit(X_train, y_train)


lin_svc = svm.LinearSVC(C=C).fit(X, y)



poly_svc = svm.SVC(kernel='poly', degree=3, C=C).fit(X, y)


h= 0.2
x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
	                     np.arange(y_min, y_max, h))

titles = ['SVC with linear kernel',
	   'LinearSVC (linear kernel)',
	    'SVC with RBF kernel',
	    'SVC with polynomial (degree 3) kernel']
 
 
for i, clf in enumerate((svc, lin_svc, rbf_svc, poly_svc)):
	 
	 plt.subplot(2, 2, i + 1)
	 plt.subplots_adjust(wspace=0.4, hspace=0.4)
 
	 Z = clf.predict(np.c_[xx.ravel(), yy.ravel()])
 
	
	 Z = Z.reshape(xx.shape)
	 plt.contourf(xx, yy, Z, cmap=plt.cm.coolwarm, alpha=0.8)
 
	
	 plt.scatter(X[:, 0], X[:, 1], c=y, cmap=plt.cm.coolwarm)
	 plt.xlabel('Length of Sepal')
	 plt.ylabel('Width of Sepal')
	 plt.xlim(xx.min(), xx.max())
	 plt.ylim(yy.min(), yy.max())
	 plt.xticks(())
	 plt.yticks(())
	 plt.title(titles[i])
 
plt.show()

print("Linear SVC Accuracy: ", svc.score(X_test,y_test)*100 )
print("RBF SVC Accuracy: ", rbf_svc.score(X_test,y_test)*100 )




####################################################################


X = np.sort(5* np.random.rand(40, 1), axis=0)
y = np.cos(X).ravel()

#print(X)
#print(y)

                   
svr_rbf = SVR(kernel='rbf', C=10, gamma=0.5)

svr_poly = SVR(kernel='poly', C=1, degree=3)

svr_lin = SVR(kernel='linear', C=10)

y_rbf = svr_rbf.fit(X, y).predict(X)
y_lin = svr_lin.fit(X, y).predict(X)
y_poly = svr_poly.fit(X, y).predict(X)

lw = 2
plt.scatter(X, y, color='red', label='Data points')
plt.plot(X, y_rbf, color='darkgreen', lw=lw, label='RBF model')
plt.plot(X, y_poly, color='black', lw=lw, label='Polynomial model of degree 3')
plt.plot(X, y_lin, color='darkorange', lw=lw, label='Linear model')

plt.xlabel('Data')
plt.ylabel('Target')
plt.title('Support Vector Regression for cos() function')

plt.legend()

#Sin cos##############################################################
X = np.sort(5* np.random.rand(50, 1), axis=0)
y = np.sin(X).ravel()

#print(X)
#print(y)

                   
svr_rbf = SVR(kernel='rbf', C=10, gamma=0.5)
svr_lin = SVR(kernel='linear', C=10)
svr_poly = SVR(kernel='poly', C=1, degree=3)
y_rbf = svr_rbf.fit(X, y).predict(X)
y_lin = svr_lin.fit(X, y).predict(X)
y_poly = svr_poly.fit(X, y).predict(X)


lw = 2
plt.scatter(X, y, color='red', label='Data points')
plt.plot(X, y_rbf, color='darkgreen', lw=lw, label='RBF model')
plt.plot(X, y_lin, color='darkorange', lw=lw, label='Linear model')
plt.plot(X, y_poly, color='black', lw=lw, label='Polynomial model of degree 3')
plt.xlabel('Data')
plt.ylabel('Target')
plt.title('Support Vector Regression for sin() function')
plt.legend()
plt.show()





