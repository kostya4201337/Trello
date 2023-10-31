import React, {useEffect, useState} from "react";
import Board from "./board/board";
import {IBoardEntity} from "../../model/entity/IBoardEntity";
import {List, ListItem} from "@mui/material";
import AddBoard from "./addBoard/AddBoard";
import {addBoardService, getBoardsService} from "../../services/board";
import {IBoardCreation} from "../../model/dto/IBoardCreation";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<IBoardEntity[]>([]);

    useEffect(() => {
        getBoards();
    }, [])

    const getBoards = () => {
        const fetchedBoards = getBoardsService();
        setBoards(fetchedBoards);
    }

    const addBoard = (newBoard: IBoardCreation) => {
        const addedBoard = addBoardService(newBoard);
        setBoards([...boards, addedBoard])
    }

    const deleteBoard = (id: number) => {
        setBoards(boards.filter(board => board.id !== id));
    }

    return (
        <div>
            <List style={{display: 'inline-flex'}}>
                    {boards.map((board) => (
                        <ListItem key={board.id}>
                            <Board board={board} deleteBoard={deleteBoard}/>
                        </ListItem>
                    ))}
                <ListItem>
                    <AddBoard addBoard={addBoard}/>
                </ListItem>
            </List>
        </div>
    );
}

export default Workspace;