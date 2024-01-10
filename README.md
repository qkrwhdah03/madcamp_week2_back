# Developers

---

KAIST 전산학부 22학번 박종모

한양대학교 생명과학과 20학번 강다희

# 사용된 기술

---

Language: JAVA,  Python

Server: Flask

Database: MySQL 

OS: Android 8.0 

(minSdk = 24, targetSdk = 34) 

IDE: Android Studio , Visual Studio

Target Device: Galaxy S7

# 프로젝트 소개

---

헬스트레이너와 회원간의 쉬운 매칭과, 트레이너의 편리한 회원 관리, 회원의 운동 등을 돕는 gym 서비스 앱입니다. 

# 기능 소개

---

### 1. 카카오 로그인

- 카카오 SDK를 이용해 카카오 계정으로 로그인 할 수 있습니다.
- 카카오 계정은 본인의 짐과외 프로필에 접근하기 위한 목적으로 사용됩니다.
- <img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/7f01621f-e59c-4635-a3de-02d1c69b0c05" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/e9af9be5-f35b-47d9-a0bb-70775bb1347e" alt="Screenshot" width="300"/>



### 2. 개인 프로필 생성

- 로그인된 카카오 계정의 고유ID를 이용해 이에 대응되는 프로필을 생성하거나 가져올 수 있습니다.
- 신분 및 앱 사용 목적에 따라 회원, 혹은 트레이너로 프로필을 생성할 수 있습니다. 이에 따라 기능 및 UI 역시 달라집니다.
- 트레이너의 경우에는 검색 탭에서 회원으로 가입된 프로필에만 접근 가능하며, 회원의 경우 오직 트레이너로 가입된 프로필에만 접근할 수 있습니다. 이에 따라 매칭 요청 또한 마찬가지입니다.
- 트레이너, 회원 여부에 따라 필요한 정보와 표시되는 정보에 차이가 있으며 따라서 프로필에 표시되는 정보에도 차이가 있습니다.
-<img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/e9f48a96-ad2b-4e0c-990e-62a8c237055c" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/aef09787-d708-4820-836f-7a7265e75e9e" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/09469f73-3d2a-4981-bdf3-34cba14f38e3" alt="Screenshot" width="300"/>



### 3. 트레이너-회원 매칭

- 트레이너/회원 계정에서는 가입된 회원/트레이너 리스트를 확인할 수 있고, 원하는 회원/트레이너에게 매칭을 요청할 수 있습니다.
- 회원/트레이너는 매칭 요청을 확인할 수 있으며, 수락 혹은 거절을 선택할 수 있습니다. 수락한 경우 트레이너-회원이 매칭됩니다.
- 트레이너/회원은 매칭된 회원/트레이너를 본인의 매칭 리스트에서 확인할 수 있습니다.
- <img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/4cdec5e2-c546-4125-9947-f5dd5040948b" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/072741ee-660c-4035-a29a-a6a0f1fc5fcc" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/11538834-5741-4660-be2a-0bcbda7ca934" alt="Screenshot 1" width="300"/>



### 4. 매칭 후 통화 및 문자

- 트레이너와 회원 매칭 이후에는 서로의 전화번호 정보를 알 수 있습니다.
- 프로필 창 밑에 있는 전화, 메세지 버튼을 이용해 해당 전화번호로 연결할 수 있습니다.
- <img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/994c7b74-556d-418c-b2c8-1c36e5263edd" alt="Screenshot 2" width="300"/>



### 5. 회원 캘린더 관리

- 회원 캘린더에서는 오늘의 식단, 오늘의 운동, 오늘의 인바디를 기록할 수 있습니다. 오늘의 식단의 경우 갤러리에서 사진을 선택하여 이미지로 기록할 수 있으며, 오늘의 운동 및 오늘의 인바디에는 운동 내용 및 인바디를 텍스트 메모로 기록할 수 있습니다.
- <img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/daa3ba2d-8094-411a-815b-2687fb9b5e71" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/e49ea24b-9bfa-4b3e-b057-193f3045d212" alt="Screenshot" width="300"/><img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/eadee4d2-38be-4308-9144-b215aae3b9fe" alt="Screenshot" width="300"/>

### 6. 개인 프로필 확인

- 트레이너, 회원 앱의 마지막 탭에서는 본인의 가입된 짐과외 프로필 정보가 표시됩니다 .
- <img src="https://github.com/qkrwhdah03/madcamp_week2_frontend/assets/139040057/78541a94-d258-40bb-bf72-f9da15e9719a" alt="Screenshot" width="300"/>
