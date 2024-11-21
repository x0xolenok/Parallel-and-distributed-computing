# MapReduce Task: Count "Attribute, Case, and Vote Lines" in `anonymous-msweb.data`

## Task Description

Write a MapReduce job that will calculate the number of "Attribute, Case, and Vote Lines" for the dataset `anonymous-msweb.data`.

[Download the dataset here.](https://archive.ics.uci.edu/dataset/4/anonymous+microsoft+web+data)

## Solution Steps

Follow the steps below to set up the Hadoop environment with Docker, upload the dataset, and execute the MapReduce job.

### Step 1: Navigate to the Project Directory

```bash
cmd E:\future generations\4 course 1 semester\Parallel computing\Hadoop\docker-hadoop
```

### Step 2: Start the Docker Containers

Start the Hadoop cluster using Docker Compose:

```bash
docker-compose up -d
```
![image](https://github.com/user-attachments/assets/83b5d74f-3b8d-4704-9727-a9700964c612)

### Step 3: Create Directory for Files in the Namenode Container

Run the following command to create a directory for storing data and the MapReduce job inside the Namenode container:

```bash
docker exec namenode mkdir -p /home/hadoop
```
![image](https://github.com/user-attachments/assets/664b2fcc-61bf-4207-af84-f49dd69302b7)

### Step 4: Copy the Dataset to the Namenode Container

Copy the dataset file anonymous-msweb.data from your local machine to the Namenode container:

```bash
docker cp "E:\future generations\4 course 1 semester\Parallel computing\Map-Reduce\anonymous-msweb.data" namenode:/home/hadoop/
```
![image](https://github.com/user-attachments/assets/1715f958-b802-41fd-88fd-518e83dee738)

### Step 5: Copy the MapReduce Job JAR File

Copy the MapReduce job JAR file MapReduce-1.0-SNAPSHOT.jar to the Namenode container:

```bash
docker cp "E:\future generations\4 course 1 semester\Parallel computing\Map-Reduce\target\Map-Reduce-1.0-SNAPSHOT.jar" namenode:/home/hadoop/
```
![image](https://github.com/user-attachments/assets/3d0d3cc3-01c8-4260-be8f-d18bfe3834b5)

### Step 6: Access the Namenode Container

Log into the Namenode container to run the following HDFS commands:
```bash
docker exec -it namenode /bin/bash
```
![image](https://github.com/user-attachments/assets/c9cd4f43-832f-4649-a934-dd4c595b7d16)

### Step 7: Leave Safe Mode

Before interacting with HDFS, leave safe mode (HDFS may automatically enter safe mode on startup):

```bash
hdfs dfsadmin -safemode leave
```
![image](https://github.com/user-attachments/assets/d9408b4a-ff26-47a7-ab86-fdade11227b3)

### Step 8: Clean the HDFS Input Directory (if it exists)

Delete the existing input directory in HDFS (if any) to avoid conflicts:

```bash
hdfs dfs -rm -r /input
```
![image](https://github.com/user-attachments/assets/972fd4d0-8976-49f0-bbad-44eda4afb8fc)


### Step 9: Create a New Input Directory in HDFS

Create a new directory in HDFS to store the dataset:
```bash
hdfs dfs -mkdir /input
```
![image](https://github.com/user-attachments/assets/44967004-8e43-448f-a809-670cf67cff88)


### Step 10: Upload the Dataset to HDFS

Upload the dataset to the newly created input directory in HDFS:
```bash
hdfs dfs -put /home/hadoop/anonymous-msweb.data /input/
```
![image](https://github.com/user-attachments/assets/2cd2f8e1-5397-4c5a-ad60-36adb15ccfd7)

### Step 11: Clean the HDFS Output Directory (if it exists)

Remove any existing output directory in HDFS to avoid conflicts with the results:
```bash
hdfs dfs -rm -r /output/result
```
![image](https://github.com/user-attachments/assets/b9639dc1-1bb0-477a-a2eb-7e872a19f267)

### Step 12: Run the MapReduce Job

Run the MapReduce job using the uploaded dataset:
```bash
yarn jar /home/hadoop/Map-Reduce-1.0-SNAPSHOT.jar org.example.Main /input/anonymous-msweb.data /output/result
```
![image](https://github.com/user-attachments/assets/c76053f4-a0e7-4599-9c28-544e1701f622)
![image](https://github.com/user-attachments/assets/8c485ea2-2182-4710-b904-7ae628cd3876)

### Step 13: View the Results

Once the job completes, view the results by reading the output file in HDFS:
```bash
hdfs dfs -cat /output/result/part-r-00000
```
![image](https://github.com/user-attachments/assets/fb37a9c4-80be-4175-a3a9-0d6ce7b94d29)
