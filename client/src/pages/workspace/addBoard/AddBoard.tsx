import React, {useEffect, useState} from 'react';
import {IBoardCreation} from "../../../model/dto/IBoardCreation";
import {AddBoardProp} from "./props";
import Button from "@mui/material/Button";
import "./style.css";
import { TextField } from '@mui/material';
import { addBoardService } from "../../../services/board";

const AddBoard: React.FC<AddBoardProp> = ({addBoard}: AddBoardProp) => {
    const [board, setBoard] = useState<IBoardCreation>({name: '', description: ''})
    const [isValid, setIsValid] = useState<boolean>(false)

    useEffect(() => {
        validateBoard(board);
    }, [board]);

    const addNewBoard = () => {
        addBoard(addBoardService(board));
        setBoard({name: '', description: ''});
    }

    const validateBoard = (board: IBoardCreation) => {
        if (board.name !== "" && board.description !== "") {
            setIsValid(true)
        } else {
            setIsValid(false)
        }
    }

    return (
        <div className={"board"}>
            <TextField
                style={{
                    marginLeft: 20,
                    marginTop: 10,
                    width: 260,
                }}
                inputProps={{
                    style: {
                        padding: 7
                    }
                }}
                variant="outlined"
                label="Name"
                value={board.name}
                onChange={({target: {value}}) => setBoard({...board, name: value})}
                type="text"
                fullWidth
            />
            <TextField
                style={{
                    marginLeft: 20,
                    marginTop: 10,
                    width: 260,
                }}
                inputProps={{
                    style: {
                        padding: 7,
                    }
                }}
                variant="outlined"
                label="Description"
                value={board.description}
                onChange={({target: {value}}) => setBoard({...board, description: value})}
                type="text"
                fullWidth
            />
            <div style={{paddingLeft: 50, paddingTop: 10, paddingBottom: 10}}>
                <Button
                    style={{
                        borderRadius: 20,
                        backgroundColor: "#ffffff",
                        fontSize: "18px",
                        borderLeft: "2px #575757 solid",
                        borderTop: "2px #575757 solid",
                    }}
                    className={"addButton"} variant="contained" onClick={addNewBoard}
                    disabled={!isValid}
                >
                    <p className={"plus"}>+</p>
                </Button>
            </div>
        </div>
    );
};

export default AddBoard;