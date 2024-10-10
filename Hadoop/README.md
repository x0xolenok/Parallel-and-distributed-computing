# Execution:
**1.Clone docker-hadoop repo from github**

- git clone https://github.com/big-data-europe/docker-hadoop

**2. Start the necessary containers using docker-compose**

- docker-compose up -d

![image_2024-10-09_21-16-25](https://github.com/user-attachments/assets/1420cd69-f0bf-42a6-bfbb-49c438b57704)

**3. Get in our master node “namenode”**

- docker exec -it namenode bash

**4. Create folder structure to allocate input files**

- hdfs dfs -mkdir -p /user/root

We can verify if it was created correctly

- hdfs dfs -ls /user/

![image_2024-10-09_21-19-36](https://github.com/user-attachments/assets/e3874893-b68a-4b4c-8db0-c0ed42937dcc)

**6. Download or create the .txt file that you want to process**

**7. Move our .jar & .txt file into the container**

- docker cp ../name.txt namenode:/tmp

![image_2024-10-09_21-22-58](https://github.com/user-attachments/assets/4394265c-de80-4fc9-8e34-1b1d7ab6ef57)

**8. Create the input folder inside our namenode container**

Get in the namenode container again
- docker exec -it namenode bash
Then
- hdfs dfs -mkdir /user/root/input

![image_2024-10-09_21-24-13](https://github.com/user-attachments/assets/4b3e0b0d-4de2-4c4f-bc32-4240e5b184bb)

**9. Copy your .txt file from /tmp to the input file**
- cd /tmp
- hdfs dfs -put name.txt /user/root/input

**10.See the results**

- hdfs dfs -cat /user/root/input/name.txt

![image_2024-10-09_21-27-26](https://github.com/user-attachments/assets/e2656c3c-19a9-4837-abeb-d1990dffab82)
