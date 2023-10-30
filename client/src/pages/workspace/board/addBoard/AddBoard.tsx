import React, {useState} from 'react';
import {IBoardEntity} from "../../../../model/entity/IBoardEntity";
import {IBoardCreation} from "../../../../model/dto/IBoardCreation";
import {AddBoardProp} from "./props";
import Button from "@mui/material/Button";
import "./style.css";
import { TextField } from '@mui/material';

const AddBoard: React.FC<AddBoardProp> = ({onCreate}: AddBoardProp) => {
    const [board, setBoard] = useState<IBoardCreation>({name: '', description: ''})

    const addNewBoard = () => {
        const newBoard: IBoardEntity = {
            id: Date.now(),
            ...board,
            createdAt: new Date(),
            updatedAt: new Date()
        }
        onCreate(newBoard);
        setBoard({name: '', description: ''});
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
                    className={"addButton"} variant="contained" onClick={addNewBoard}>
                    <p className={"plus"}>+</p>
                </Button>
            </div>
        </div>
    );
};

export default AddBoard;