from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

import asyncio
from prisma import Prisma

app = FastAPI()

origins = [
    "http://localhost:3000",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/hello")
def hello():
    return {"message": "Hello Rest API Server"}

@app.get("/read")
def read_user():
    user = asyncio.run(read_user_db())
    return user

@app.get("/create")
def create_user():
    asyncio.run(create_user_db())
    return {"message": "Post Completed"}


async def read_user_db() -> None:
    db = Prisma()
    await db.connect()

    user = await db.user.find_many(
        where = {
            'name': 'philip',
        }
    )
    assert user is not None
    # print(f'found user: {user.model_dump_json(indent=2)}')

    return user
    await db.disconnect()
    
async def create_user_db() -> None:
    db = Prisma()
    await db.connect()

    user = await db.user.create(
        {
            'email': 'leeig0531@gmail.com',
            'name': 'philip',
        }
    )
    print(f'created user: {user.model_dump_json(indent=2)}')

    found = await db.user.find_unique(where={'id': user.id})
    assert found is not None
    print(f'found user: {found.model_dump_json(indent=2)}')

    await db.disconnect()