2021.08.23
2021.08.25
2021.09.06
2021.09.23
2021.10.03

[진행상황]
N3ML 진행중
- 데이터 로드 추가
- SoftLIF 트레이닝된 데이터 로드 기능 추가

파일 분리 진행중
- 빈 파일 생성 (8개파일)
- Linear Regression에 한하여 py 소스코드생성
- MNIST py소스코드 생성 0907
- manifest.json 수정 (프로토수준.. 0825)
- SNN에서 각 런타임명에 맞는 파일 생성하여 런타임 구분 완료
- NengoDL에서 모델 예시 GUI 추가완료
- html 파일 없는 경우 생성완료 0906
- 런타임 파라미터 설정 먼저 진행한 후 ui 수정가능하도록 수정 0906
- 파일분리 완료 0912
  . js, html 3개씩 추가
  . NengoDL인경우 html, js 소스코드내용 수정  
- 아키텍처 추가 완료 0914
- Linear, MNIST 예시모델 추가 완료 0916
- MNIST py를 레이어수에맞게 동적 변경 수정완료 0923

NAAL 추가
- naal 라이브러리 로드 추가완료 0906

HW 툴바 추가 1003


[TODO]
- Rogistic 예시모델 추가
- 트레이닝/테스트 분리
- NAAL 최적화 GUI 추가


ISSUE
- except Exception as e: 를 jython이 감당못하는듯.. except Exception, e:로 수정하긴했는데 가능한지는 확인해보아야 함. 0825

Python Platform
C:\ProgramData\Anaconda3\envs\tf220\python.exe



NAAL 0906 정재혁
넘파이 1.16.6 사용

nengo 3.1.0 사용

nengo_dl 3.4.1 사용

tensorflow 2.1.0버전 사용 후:
 - wget https://github.com/Qengineering/Tensorflow-Raspberry-Pi/raw/master/tensorflow-2.1.0-cp37-cp37m-linux_armv7l.whl
 - pip3 install tensorflow-2.1.0-cp37-cp37m-linux_armv7l.whl

앞에 경로 까먹음(만약 텐서 오류가 난다면 오류의 경로를 따라서) compat.py 에서 텐서 2.3.0RC 을 2.1.0RC로 수정하여 사용

07. de1-soc보드에 opt경로에 02. NAAL_de1_version2.1.tar 파일 전송후 압축해제

08. 보드의 비트스트림 파일은 opt/NAAL_de1/NAAL_de1/lib 경로로 복사 (기존 비트스트림 파일은 opt/nengo-de1/lib에 있음)
 - de1보드의 opt/nengo-de1/lib에서 수행
    - scp –c aes256-ctr pes_relu_rate.aocx root@192.168.0.236:/opt/NAAL_de1/NAAL_de1/lib
    - scp –c aes256-ctr pes_relu_spiking.aocx root@192.168.0.236:/opt/NAAL_de1/NAAL_de1/lib
    - scp –c aes256-ctr pyhost_pes.so root@192.168.0.236:/opt/NAAL_de1/NAAL_de1/lib