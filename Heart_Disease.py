
# coding: utf-8

# In[1]:


import pandas as pd


# In[3]:


df = pd.read_csv("C:\Users\HITESH\Desktop\PROJECTSFINAL\Heart\heart_dataset.csv")


# In[4]:


df


# In[5]:


import numpy as np


# In[6]:


import seaborn as sn


# In[7]:


import matplotlib.pyplot as plt


# In[8]:


from sklearn.model_selection import train_test_split


# In[9]:


from sklearn.neighbors import KNeighborsClassifier


# In[10]:


feature_names_df = ['age', 'sex', 'cp', 'trestbps', 'chol', 'fbs', 'restecg', 'thalach', 'exang', 'oldpeak', 'slope', 'ca','thal']

X_features = df[feature_names_df]
y_features = df['result']


# In[11]:


X_train, X_test, y_train, y_test = train_test_split(X_features, y_features, random_state=0)


# In[12]:


from sklearn.preprocessing import MinMaxScaler


# In[13]:


scaler = MinMaxScaler()


# In[14]:


X_train_scaled = scaler.fit_transform(X_train)


# In[15]:


knn = KNeighborsClassifier(n_neighbors = 5)


# In[17]:


knn.fit(X_train_scaled, y_train)


# In[1]:


print('Accuracy of K-NN classifier on training set: {:.2f}'
     .format(knn.score(X_train_scaled, y_train)))


# In[ ]:


print('Accuracy of K-NN classifier on test set: {:.2f}'
     .format(knn.score(X_test_scaled, y_test)))

