# -*- coding: utf-8 -*-
"""recommendation_system.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1yAg5adiLrbnK4WTPo1StRdoMVKLQQANR
"""

#!pip install openai langchain

# Import necessary libraries
from langchain.embeddings import FakeEmbeddings
import pandas as pd
OPENAI_API_KEY = env_banake_usme_daal_bsdk
fake_embeddings = FakeEmbeddings(size=100)

cities_df = pd.read_csv("cities.csv")

# Initialize an empty list to store embeddings
embeddings_list = []

# Iterate over each city in the DataFrame
for index, row in cities_df.iterrows():
    city_name = row['name_of_city']

    city_embedding = fake_embeddings.embed_query(city_name)

    embeddings_list.append(city_embedding)

import numpy as np

embeddings_list = np.array(embeddings_list).astype("float32")
embeddings_list

#!pip install faiss-cpu

import faiss as f

index = f.IndexFlatL2(fake_embeddings.size)
index.add(embeddings_list)

testing =   100000
test = fake_embeddings.embed_query(testing)
test = np.array(([test])).astype("float32")

test

k = 10
dist, indices = index.search(test, 10)

indices

cities_df[175]

