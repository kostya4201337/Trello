import React, {useState} from "react";
import Board from "./board/board";
import {BoardEntity} from "../../model/entity/BoardEntity";
import {List, ListItem} from "@mui/material";
import AddBoard from "./board/AddBoard";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<BoardEntity[]>([
        {id: 1, name: 'white board', description: 'mega ultra super cool red board', createdAt: new Date(), updatedAt: new Date()},
        {id: 2, name: 'board2', description: 'boardDesc2', createdAt: new Date(), updatedAt: new Date()}
    ]);

    const addBoard = (newBoard: BoardEntity) => {
        setBoards([...boards, newBoard]);
    }

    const deleteBoard = (id: number) => {
        const updatedBoards = boards.filter(board => board.id !== id);
        setBoards(updatedBoards)
    }

    return (
        <div style={{verticalAlign: 'middle'}}>
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