from sqlalchemy import create_engine
import threading


class DatabaseConnectionSingleton:
    _engine = None
    _lock = threading.Lock()

    @classmethod
    def get_engine(cls):
        if cls._engine is None:
            with cls._lock:
                if cls._engine is None:
                    DATABASE_URL = "postgresql+psycopg2://postgres:anaiman@localhost:5432/msa_data"
                    cls._engine = create_engine(DATABASE_URL)
        return cls._engine

def get_database_connection():
    return DatabaseConnectionSingleton.get_engine()
