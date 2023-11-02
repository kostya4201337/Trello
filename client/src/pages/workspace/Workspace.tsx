import React, {useEffect, useState} from "react";
import Board from "./board/Board";
import {IBoard, IBoardCreation} from "../../model/Board";
import Box from '@mui/material/Box';
import AddBoard from "./addBoard/AddBoard";
import "./styles.css";
import {addBoardService, deleteBoardService, getBoardsService} from "../../services/board";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<IBoard[]>([]);

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
        deleteBoardService(id);
        setBoards(boards.filter(board => board.id !== id));
    }

    return (
        <Box className={"boardList"} sx={{ alignItems: 'flex-start'}}>
            {boards.map((board) => (
                <Board board={board} deleteBoard={deleteBoard}/>
            ))}
            <AddBoard addBoard={addBoard}/>
        </Box>
    );
}

export default Workspace;