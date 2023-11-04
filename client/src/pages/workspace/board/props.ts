import {IBoard} from "../../../model/Board";

export type DeleteBoardProp = {
    board: IBoard,
    deleteBoard: (id: number) => void
}