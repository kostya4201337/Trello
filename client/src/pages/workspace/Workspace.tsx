import React, {useEffect, useState} from "react";
import Board from "./board/board";
import {IBoardEntity} from "../../model/entity/IBoardEntity";
import {List, ListItem} from "@mui/material";
import AddBoard from "./board/addBoard/AddBoard";
import {getBoardsService} from "../../services/board";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<IBoardEntity[]>([]);

    useEffect(() => {
        getBoards();
    }, [])

    const getBoards = () => {
        const fetchedBoards: IBoardEntity[] = getBoardsService();
        setBoards(fetchedBoards);
    }

    const addBoard = (newBoard: IBoardEntity) => {
        setBoards([...boards, newBoard]);
    }

    const deleteBoard = (id: number) => {
        const updatedBoards = boards.filter(board => board.id !== id);
        setBoards(updatedBoards)
    }

    return (
        <div>
            <List style={{display: 'inline-flex'}}>
                    {boards.map((board) => (
                        <ListItem key={board.id}>
                            <Board board={board} onDelete={deleteBoard}/>
                        </ListItem>
                    ))}
                <ListItem>
                    <AddBoard onCreate={addBoard}/>
                </ListItem>
            </List>
        </div>
    );
}

export default Workspace;