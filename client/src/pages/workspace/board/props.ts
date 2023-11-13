import {IBoard} from "../../../model/Board";
export type BoardProps = {
    board: IBoard,
    deleteBoard: (id: number) => void
    updateName: (board: IBoard, text: string) => void
    updateDescription: (board: IBoard, text: string) => void
}