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
    users = asyncio.run(read_user_db())
    return users

@app.post("/create")
def create_user(data: dict):
    asyncio.run(create_user_db(data))
    return {"message": "Post Completed"}

@app.put("/update/{user_id}")
def update_user(user_id: int, data: dict):
    asyncio.run(update_user_db(user_id, data))
    return {"message": "Update Completed"}

@app.delete("/delete/{user_id}")
def delete_user(user_id: int):
    asyncio.run(delete_user_db(user_id))
    return {"message": "Delete Completed"}


async def read_user_db() -> None:
    db = Prisma()
    await db.connect()

    users = await db.user.find_many()
    
    assert users is not None

    await db.disconnect()

    return users
    
async def create_user_db(data: dict) -> None:
    db = Prisma()
    await db.connect()

    user = await db.user.create(
        {
            'email': data.get('email'),
            'name': data.get('name'),
        }
    )
    print(f'created user: {user.model_dump_json(indent=2)}')

    found = await db.user.find_unique(where={'id': user.id})
    assert found is not None
    print(f'found user: {found.model_dump_json(indent=2)}')

    await db.disconnect()

async def update_user_db(user_id: int, data: dict) -> None:
    db = Prisma()
    await db.connect()

    user = await db.user.find_unique(where={'id': user_id})

    if user: 
        updated_user = await db.user.update(
            where = {
                'id': user_id,
            },
            data = {
                'email': data.get('email', user.email),
                'name': data.get('name', user.name),
            }
        )
        print(f'updated user: {updated_user.model_dump_json(indent=2)}')
    else:
        print(f'User with id {user_id} not found')


    await db.disconnect() 

async def delete_user_db(user_id: int) -> None:
    db = Prisma()
    await db.connect()

    user = await db.user.find_unique(where={'id': user_id})
    if user:
        deleted_user = await db.user.delete(
            where = {
                'id': user_id,
            }
        )
        print(f'deleted user: {deleted_user.model_dump_json(indent=2)}')
    else:
        print(f'User with id {user_id} not found')

    await db.disconnect()