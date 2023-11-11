import React, {useEffect, useState} from "react";
import Board from "./board/Board";
import {IBoard, IBoardCreation} from "../../model/Board";
import Box from '@mui/material/Box';
import AddBoard from "./addBoard/AddBoard";
import "./styles.css";
import {boxSx} from "./styles";
import {addBoardService, deleteBoardService, getBoardsService, updateBoardService} from "../../services/board";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<IBoard[]>([]);

    useEffect(() => {
        getBoards().then();
    }, [])

    const getBoards = async () => {
        const fetchedBoards = await getBoardsService();
        setBoards(fetchedBoards);
    }

    const addBoard = async (newBoard: IBoardCreation) => {
        const addedBoard = await addBoardService(newBoard);
        if (addedBoard !== null) {
            setBoards([...boards, addedBoard]);
        }
    }

    const deleteBoard = async (id: number) => {
        await deleteBoardService(id);
        await getBoards();
    }

    const updateBoardName = async (board: IBoard, text: string) => {
        await updateBoardService(
            board.id,
            {name: text, description: board.description}
        );
    }

    const updateBoardDescription = async (board: IBoard, text: string) => {
        await updateBoardService(
            board.id,
            {name: board.name, description: text}
        );
    }

    return (
        <Box className={"boardList"} sx={boxSx}>
            {boards.map((board) => (
                <Board key={board.id} board={board} deleteBoard={deleteBoard} updateName={updateBoardName} updateDescription={updateBoardDescription}/>
            ))}
            <AddBoard addBoard={addBoard}/>
        </Box>
    );
}

export default Workspace;