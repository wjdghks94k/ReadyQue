Ready-Que-Android
=================
A Study on Coordinate Extraction and Ball Stroke Posture Analysis Using 6-Axis Gyro Sensor
-----------------

In this study, we designed a system to allow first time users of billiards to practice correct stroke position alone. 
Using a six-axis gyro sensor, acceleration, gyro, and geomagnetic values are collected by Android-to-gyro sensor BLE communication.
The velocity, angle, and direction of the stroke are calculated to show the similarity rate. In addition, the mobile is vibrated to give a feeling of impact when stroking.
Using the touched value of the sensor, the user can actually see the target point of the hit in real time on the mobile. At the same time, the previously calculated similarity rate can be confirmed by a graph and a numerical value. It helps you learn the correct stroke posture through feedback compared to the best posture.

This source code can be compiled with Android Studio and Gradle. 

역할 분담
---------
##### 조형준(팀장): 
1. DB구축, PHP서버 구축, 피드백 알고리즘 
2. 특허-MySQL과 PHP서버
3. 논문-서론

##### 김정환: 
1. 아두이노 터치센서 블루투스 통신 연동, BLE통신 연동, GPS기반 위치서비스, 어플 디자인 관리 
2. 특허-동영상기능과 지도기능 
3. 논문-요구사항과 시스템구조

##### 김진형: 
1. 유사율 알고리즘, 필터링 및 그래프 
2. 특허-차트출력과 유사율알고리즘
3. 논문-자이로센서의 각도변화와 상보필터 그리고 피드백과 EditDistance 알고리즘

##### 정윤호: 
1. 속도 알고리즘, 신호 및 주파수 분석
2. 특허-배열길이 맞추는 방법과 속도
3. 논문-주파수
