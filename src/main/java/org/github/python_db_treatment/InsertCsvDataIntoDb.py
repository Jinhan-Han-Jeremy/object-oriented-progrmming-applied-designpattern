import pandas as pd
import mysql.connector
from mysql.connector import Error

# MySQL 연결 설정
def create_connection():
    try:
        connection = mysql.connector.connect(
            host='localhost',  # MySQL 서버 주소
            database='java_hr_db',  # 데이터베이스 이름
            user='root',  # MySQL 사용자 이름
            password='57575han'  # MySQL 비밀번호
        )
        if connection.is_connected():
            print("MySQL 데이터베이스에 성공적으로 연결되었습니다.")
        return connection
    except Error as e:
        print(f"Error: '{e}' 발생")
        return None

# 테이블 생성 함수
def create_tables(connection, create_table):
    cursor = connection.cursor()
    
    # 테이블 생성 쿼리 실행
    cursor.execute(create_table)
    
    connection.commit()
    print("테이블이 성공적으로 생성되었습니다.")
    cursor.close()

# 테이블 초기화 함수 (기존 데이터 삭제)
def clear_table(connection, table_name):
    cursor = connection.cursor()
    cursor.execute(f"DELETE FROM {table_name};")  # 테이블의 모든 데이터를 삭제
    connection.commit()
    print(f"{table_name} 테이블의 기존 데이터가 삭제되었습니다.")
    cursor.close()

# task 테이블에 데이터를 삽입하는 함수
def insert_task_csv_to_mysql(csv_file, connection):
    data = pd.read_csv(csv_file)
    # NaN 값을 None으로 변환
    data = data.where(pd.notnull(data), None)
    
    insert_query = """
    INSERT INTO tasks (name, employees, difficulty, requirements)
    VALUES (%s, %s, %s, %s)
    """

    cursor = connection.cursor()
    for _, row in data.iterrows():
        cursor.execute(insert_query, (row['name'], row['employees'], row['difficulty'], row['requirements']))
    
    connection.commit()
    print("task 테이블에 CSV 데이터가 성공적으로 삽입되었습니다.")
    cursor.close()

# tasks_history 테이블에 데이터를 삽입하는 함수
def insert_tasks_history_csv_to_mysql(csv_file, connection):
    data = pd.read_csv(csv_file)
    # NaN 값을 None으로 변환
    data = data.where(pd.notnull(data), None)

    insert_query = """
    INSERT INTO tasks_history (name, teammembers, available_jobs, finished_days, state, requirements_satisfied)
    VALUES (%s, %s, %s, %s, %s, %s)
    """

    cursor = connection.cursor()
    for _, row in data.iterrows():
        cursor.execute(insert_query, (
            row['name'], 
            row['teammembers'], 
            row['available_jobs'], 
            row['finished_days'] if pd.notnull(row['finished_days']) else None, 
            row['state'], 
            row['requirements_satisfied']
        ))
    
    connection.commit()
    print("tasks_history 테이블에 CSV 데이터가 성공적으로 삽입되었습니다.")
    cursor.close()

'''
# workstream_info 테이블에 데이터를 삽입하는 함수
def insert_workstream_info_csv_to_mysql(csv_file, connection):
    data = pd.read_csv(csv_file)

    insert_query = """
    INSERT INTO workstream_info (workstream, available_jobs)
    VALUES (%s, %s)
    """

    cursor = connection.cursor()
    for _, row in data.iterrows():
        cursor.execute(insert_query, (row['workstream'], row['available_jobs']))
    
    connection.commit()
    print("workstream_info 테이블에 CSV 데이터가 성공적으로 삽입되었습니다.")
    cursor.close()
'''

# tasks 테이블의 id와 작업 이름을 가져오기
def get_task_ids(connection):
    cursor = connection.cursor()
    cursor.execute("SELECT id, name FROM tasks ORDER BY id")
    tasks = cursor.fetchall()
    cursor.close()
    return tasks  # [(id, name), ...]

# team_member 테이블에 task_id 기반 컬럼 추가
def add_task_id_columns_to_team_member(connection, task_list):
    cursor = connection.cursor()
    
    for task_id,task_name in task_list:
        # task_id를 기반으로 컬럼 이름 생성 (예: task_1, task_2)
        column_name = f"{task_name}_{task_id}"
        print(task_id, task_name)
        # team_member 테이블에 컬럼 추가 (INT 타입, 기본값 0)
        alter_query = f"ALTER TABLE team_member ADD COLUMN `{column_name}` INT DEFAULT 0"
        cursor.execute(alter_query)
    
    ##fetch_team_member_with_alias(cursor, task_list)

    connection.commit()
    cursor.close()
    
def fetch_team_member_with_alias(cursor, task_list):
    select_query = "SELECT "
    
    # task 컬럼에 대해 alias 적용
    select_parts = [f"task_{task_id} AS 'Task: {task_name}'" for task_id, task_name in task_list]
    select_query += ", ".join(select_parts)
    select_query += " FROM team_member"
    
    cursor.execute(select_query)
    results = cursor.fetchall()
    
   
def main():
    connection = create_connection()

        # tasks 테이블 생성
    create_tasks_table = """
    CREATE TABLE IF NOT EXISTS tasks (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        employees VARCHAR(255),
        difficulty INT,
        requirements TEXT
    );
    """
    
    # workstream_info 테이블 생성
    create_workstream_info_table = """
    CREATE TABLE IF NOT EXISTS workstream_info (
        id INT AUTO_INCREMENT PRIMARY KEY,
        workstream TEXT,
        available_jobs VARCHAR(255)
    );
    """
    
    # tasks_history 테이블 생성
    create_tasks_history_table = """
    CREATE TABLE IF NOT EXISTS tasks_history (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        teammembers VARCHAR(255),
        available_jobs VARCHAR(255),
        finished_days INT,
        state VARCHAR(50),
        requirements_satisfied BOOLEAN
    );
    """
    
    # teamMember 테이블 생성
    create_team_member_table = """
    CREATE TABLE IF NOT EXISTS team_member (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        role VARCHAR(255),
        level INT,
        state VARCHAR(50)
    );
    """
    
    create_tables(connection, create_tasks_table)
    create_tables(connection, create_workstream_info_table)
    create_tables(connection, create_tasks_history_table)
    create_tables(connection, create_team_member_table)
    
    if connection is not None:
        # 테이블 초기화 (기존 데이터 삭제)
        clear_table(connection, 'tasks')
        clear_table(connection, 'tasks_history')
        ##clear_table(connection, 'workstream_info')

        # CSV 파일 경로
        task_csv_file = 'C:\\Users\\USER\\Downloads\\assigned_tasks.csv'
        tasks_history_csv_file = 'C:\\Users\\USER\\Downloads\\tasks_history.csv'
        ##workstream_info_csv_file = 'C:\\Users\\USER\\Downloads\\workstream_info.csv'

        # CSV 파일을 각 테이블에 삽입
        insert_task_csv_to_mysql(task_csv_file, connection)
        insert_tasks_history_csv_to_mysql(tasks_history_csv_file, connection)
        ##insert_workstream_info_csv_to_mysql(workstream_info_csv_file, connection)

        # tasks 테이블에서 작업 ID와 이름 가져오기
        task_list = get_task_ids(connection)
        print(f"추가할 컬럼: {task_list}")
        
        # team_member 테이블에 새로운 컬럼 추가 (state 다음에 삽입됨)
        add_task_id_columns_to_team_member(connection, task_list)
        
        connection.close()

if __name__ == "__main__":
    main()
    
    
    

