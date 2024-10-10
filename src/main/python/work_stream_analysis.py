import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from InsertCsvDataIntoDb import create_connection

# 빈 DataFrame 생성 (컬럼 이름 지정)
tasks_info = pd.DataFrame(columns=['name', 'difficulty', 'requirements'])

# 분석할 텍스트
input_text = """
회사는 새로운 소프트웨어 개발 프로젝트를 시작하며, 프로젝트 목표와 범위 설정으로 명확한 목표와 작업 범위를 정의한 뒤, 일정과 예산 계획 수립을 통해 필요한 자원과 시간을 산정합니다. 이어 팀 구성 및 역할 할당으로 인력을 배치하고, 프로젝트 킥오프 미팅에서 모든 계획을 공유하여 프로젝트를 본격적으로 시작합니다.
"""

# TF-IDF 기반 텍스트 분석 함수
def match_workstream_to_task(workstream_text, task_names):
    vectorizer = TfidfVectorizer()
    task_vectors = vectorizer.fit_transform(task_names)
    workstream_vector = vectorizer.transform([workstream_text])

    # 코사인 유사도 계산
    similarities = cosine_similarity(workstream_vector, task_vectors).flatten()

    # 유사도가 높은 상위 3개 작업 선택
    matched_indices = similarities.argsort()[-3:][::-1]
    return matched_indices

def fetch_tasks_data():
    global tasks_info  # 전역 변수를 사용하도록 설정
    connection = create_connection()  # InsertCsvDataIntoDb.py의 create_connection() 호출
    if connection is None:
        print("DB 연결에 실패했습니다.")
        return

    try:
        cursor = connection.cursor()
        # SQL 쿼리 작성
        query = "SELECT name, difficulty, requirements FROM tasks"
        cursor.execute(query)  # 쿼리 실행

        # 쿼리 결과를 가져오기
        rows = cursor.fetchall()
        # 결과를 DataFrame에 추가
        # 데이터를 DataFrame에 추가
        for row in rows:
            tasks_info = tasks_info._append({
                'name': row[0],
                'difficulty': row[1],
                'requirements': row[2]
            }, ignore_index=True)

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL 연결이 닫혔습니다.")

# analyzed_texts 함수: 분석된 작업 정보를 반환하는 함수
def analyzed_texts():
    # 함수 실행
    fetch_tasks_data()

    # 결과 저장을 위한 DataFrame
    results = pd.DataFrame(columns=['name', 'difficulty', 'employees'])

    # input_text와 tasks_info의 name을 비교하여 매칭된 인덱스 찾기
    matched_indices = match_workstream_to_task(input_text, tasks_info['name'])

    # 결과 출력 및 results에 매칭된 작업 추가
    for index in matched_indices:
        print("작업들 : ", tasks_info.iloc[index]['name'])
        results = results._append(tasks_info.iloc[index], ignore_index=True)  # 결과를 DataFrame에 추가

    # 결과 DataFrame 반환
    return results

# task_names_from_analyzed_texts 함수: analyzed_texts 함수에서 name 컬럼을 리스트로 반환
def task_names_from_analyzed_texts():
    results = analyzed_texts()  # analyzed_texts에서 DataFrame을 받아옴
    return results['name'].tolist()  # name 컬럼을 리스트로 변환하여 반환

def main():
    # 함수 실행
    fetch_tasks_data()

    # 결과 저장을 위한 DataFrame
    results = pd.DataFrame(columns=['name', 'difficulty', 'requirements'])

    # input_text와 tasks_info의 name을 비교하여 매칭된 인덱스 찾기
    matched_indices = match_workstream_to_task(input_text, tasks_info['name'])

    # 결과 출력 및 results에 매칭된 작업 추가
    for index in matched_indices:
        print("작업들 : ", tasks_info.iloc[index]['name'])
        results = results._append(tasks_info.iloc[index], ignore_index=True)  # 결과를 DataFrame에 추가

    # 최종 results 출력
    print(results)
    print(task_names_from_analyzed_texts())

if __name__ == "__main__":
    main()
